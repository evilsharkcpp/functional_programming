(ns task-1.4
  (:use task-1.params))
; Задача 1.4.
; Изменить решение задачи 1.1/1.2 таким образом, чтобы вместо
; рекурсивных вызовов использовались map/reduce/filter

(load-file "params.clj")

; добавляет отфильтрованные элементы списка l к концу строки s
(defn append-letter[s l]
  (map (fn [letters] (str s (first letters)))
       (filter (fn [letter] (not= (str(last s)) letter)) l )
       ))

(defn make-list-of-strings[alp l]
  ; reduce нужен, чтобы "выпрямить массив"
  ; ((a b) (c d) (e f)) => (a b c d e f)
  (reduce concat
          (map (fn [s] (append-letter s alp)) l)
          ))

(defn task-1-4
  ([alp acc i]
   ; пока не достигли нужной длины сочетаний
   (reduce (fn [acc i]
             (make-list-of-strings alp acc)
             ) alp (range 1 N))
   )
  ([alp i]
   (if (> i 0)
     (task-1-4 alp alp i)
     '()))
  )

(println "\nRun task")
(def res (task-1-4 alphabet N))
(println res)