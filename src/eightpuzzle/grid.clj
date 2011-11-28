(ns eightpuzzle.grid
  (:use [clojure.math.numeric-tower]
        [clojure.math.combinatorics]))

(defn fvalue [grid]
  (+ (:g grid) (:h grid)))

(defn final-pos [x n]
  [(floor (/ (dec x) n))
   (mod (dec x) n)])

(defn manhattan-distance [apos bpos]
  (+ (abs (- (apos 0) (bpos 0)))
     (abs (- (apos 1) (bpos 1)))))

(defn tile-value 
  ([tiles pos] (tile-value tiles (pos 0) (pos 1)))
  ([tiles r c] ((tiles r) c)))

(defn grid-assoc
  ([tiles pos value] (grid-assoc tiles (pos 0) (pos 1) value))
  ([tiles r c value] 
    (let [newrow (assoc (tiles r) c value)] 
      (assoc tiles r newrow))))

(defn calc-h [tiles]
  (let [n (count tiles)]
    (apply + (map 
      (fn [x] 
        (let [r (floor (/ x n)) c (mod x n) 
              value (tile-value tiles r c)]
          (if (nil? value) 0
            (manhattan-distance [r c] (final-pos value n))))) 
      (range 0 (* n n))))))

(defn make-grid [tiles g]
  {:tiles tiles
   :g g
   :h (calc-h tiles)})

(defn shift-tile [tiles src dest]
  (if (nil? (tile-value tiles dest)) 
    (let [temptiles (grid-assoc tiles dest (tile-value tiles src))]
      (grid-assoc temptiles src nil))
    tiles))

(defn empty-slot [tiles]
  (let [n (count tiles) 
        positions (cartesian-product (range 0 n) (range 0 n))]
    (vec (first (filter 
      (fn [pos] 
        (nil? (tile-value tiles (vec pos)))) 
      positions)))))
