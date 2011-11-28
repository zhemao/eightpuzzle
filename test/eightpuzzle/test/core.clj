(ns eightpuzzle.test.core
  (:use [eightpuzzle.core] [eightpuzzle.grid])
  (:use [clojure.test]))

(deftest calc-h-test
  (is (= 0 (calc-h [[1 2 3] [4 5 6] [7 8 nil]])))
  (is (= 2 (calc-h [[1 3 2] [4 5 6] [7 8 nil]])))
  (is (= 3 (calc-h [[1 2 3] [4 6 nil] [7 8 5]]))))

(deftest make-grid-test
  (let [tiles [[1 2 3] [4 5 6] [7 8 nil]]
        grid (make-grid tiles 0)
        grid2 (make-grid tiles 1)]
    (is (= 0 (fvalue grid)))
    (is (= 1 (fvalue grid2)))))

(deftest grid-assoc-test
  (let [tiles [[1 2 3] [4 5 6]]]
    (is (= [[2 2 3] [4 5 6]] (grid-assoc tiles 0 0 2)))))

(deftest shift-tiles-test
  (let [tiles [[1 2 3] [4 5 6] [7 nil 8]]
        newtiles (shift-tile tiles [2 2] [2 1])]
    (is (= [[1 2 3] [4 5 6] [7 8 nil]] newtiles))))

(deftest empty-slot-test
  (let [tiles [[1 2 3] [4 5 6] [7 8 nil]]]
    (is (= [2 2] (empty-slot tiles)))))

(deftest branch-grid-test
  (let [tiles [[1 2 3] [4 nil 6] [7 8 5]]]
    (println (branch-grid (make-grid tiles 0)))))
