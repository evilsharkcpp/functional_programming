(ns task-1.3
  (:use task-1.params))
; Задача 1.3
; Определить функции my-map и my-filter, аналогичные map (для одного списка) и filter,
; выразив их через reduce и базовые операции над списками (cons, first, concat и т.п.)

; с анонимной функцией
(defn my-map [f l]
  (seq (reduce (fn [acc x] (conj acc (f x)))
               []
               l)))
(println (my-map inc (list 1 2 3 4)))

; с анонимной функцией
(defn my-filter[f l]
  (seq (reduce (fn [acc x]
                 (if (f x)
                   (conj acc x)
                   acc))
               []
               l)))
(println (my-filter (fn [x] (not= 0 (mod x 3)))
                     (list 1 2 3 4)))