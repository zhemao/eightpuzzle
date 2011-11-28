(ns eightpuzzle.core
  (:use [eightpuzzle.grid]))


(defn branch-grid [grid]
  (let [{:keys [tiles g]} grid
        [r c] (empty-slot tiles) 
        listref (atom [])]
    (if (> r 0) (swap! listref conj (shift-tile tiles [(dec r) c] [r c])))
    (if (< r 2) (swap! listref conj (shift-tile tiles [(inc r) c] [r c])))
    (if (> c 0) (swap! listref conj (shift-tile tiles [r (dec c)] [r c])))
    (if (< c 2) (swap! listref conj (shift-tile tiles [r (inc c)] [r c])))
    (map #(make-grid % (+ g 1)) @listref)))

