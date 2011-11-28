(ns eightpuzzle.core
  (:use [eightpuzzle.grid]))

(def branch-grid [grid]
  (let [pos (empty-slot (grid :tiles)) 
        accumulator (atom [])]
    (if (= 

