(ns eightpuzzle.core
  (:use [eightpuzzle.grid]
        [eightpuzzle.queue]))


(defn branch-grid [grid]
  (let [{:keys [tiles g]} grid
        [r c] (empty-slot tiles) 
        listref (atom [])]
    (if (> r 0) (swap! listref conj (shift-tile tiles [(dec r) c] [r c])))
    (if (< r 2) (swap! listref conj (shift-tile tiles [(inc r) c] [r c])))
    (if (> c 0) (swap! listref conj (shift-tile tiles [r (dec c)] [r c])))
    (if (< c 2) (swap! listref conj (shift-tile tiles [r (inc c)] [r c])))
    (map #(make-grid % (+ g 1)) @listref)))

(defn solution? [grid]
  (= 0 (:h grid)))

(defn solve-puzzle [starting-tiles]
  (loop [queue (make-grid-queue [(make-grid starting-tiles 0)])]
    (let [grid (first-grid queue)]
      (println (:h grid))
      (if (solution? grid) grid
        (let [newgrids (branch-grid grid)
              newqueue (add-all-grids (pop queue) newgrids)]
          (recur newqueue))))))

(defn -main [& args]
  (let [start [[3 6 1] [nil 8 2] [4 5 7]]
        solution (solve-puzzle start)]
    (println "Solved Puzzle!")
    (print-grid solution)))

