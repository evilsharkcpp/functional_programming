(ns task-1.4
  (:use task-1.params))
; Задача 1.4.
; Изменить решение задачи 1.1/1.2 таким образом, чтобы вместо
; рекурсивных вызовов использовались map/reduce/filter

(load-file "params.clj")

; добавляет отфильтрованные элементы списка l к концу строки s
(defn append-letter[s l]
  ;сокращение для определения анонимной функции + сокращение для аргументов
  (map #(str s (first %))
       (filter #(not= (str(last s)) %) l )
       ))

(defn make-list-of-strings[alp l]
  ; reduce нужен, чтобы "выпрямить массив"
  ; ((a b) (c d) (e f)) => (a b c d e f)
  (reduce concat
          (map #(append-letter % alp) l)
          ))

(defn task-1-4
  ([alp i acc]
   ; пока не достигли нужной длины сочетаний
   (if (> i 1)
     (recur alp
            (dec i)
            (make-list-of-strings alp acc))
     acc))
  ([alp i]
   (task-1-4 alp i alp))
  )

(println "\nRun task")
(def res (task-1-4 alphabet N))
(println res)