(ns eightpuzzle.queue
  (:use [clojure.data.priority-map] 
        [eightpuzzle.grid]))

(defn add-grid [queue grid]
  (assoc queue grid (fvalue grid)))

(defn add-all-grids [queue grids]
  (reduce
    (fn [newqueue grid] (add-grid newqueue grid))
    queue grids))

(defn make-grid-queue [grids]
  (add-all-grids (priority-map) grids))

(defn first-grid [queue]
  (first (peek queue)))

