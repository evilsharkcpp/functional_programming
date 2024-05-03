(ns task-1.1
  (:use task-1.params))

; 1. Базовые операции над структурами данных
; Общее условие:Задан набор символов и число n.
; Опишите функцию, которая возвращает список всех строк длины n, состоящих
; из этих символов и не содержащих двух одинаковых символов, идущих подряд.
; Пример: Для символов 'а', 'b', 'c' и n=2 результат должен быть
;("ab" "ac" "ba" "bc" "ca" "cb") с точностью до перестановки.

; Задача 1.1.
; Решите задачу с помощью элементарных операций над последовательностями и рекурсии.
(load-file "params.clj")

;; добавляет элементы списка l к концу строки s
(defn append-letter [s l]
  ; если список не пуст
  (if (> (count l) 0)
    ; если последний символ строки совпал с элементом списка
    (if (= (str (last s)) (first l))
      ; пропускаем его
      (append-letter s (rest l))
      ; добавляем элемент списка к строке и идём дальше
      (concat
        (list (str s (first l)))
        (append-letter s (rest l))
        )
      )))

(defn make-list-of-strings [alp l]
  ; пока список не пуст
  (if (> (count l) 0)
    ; к каждому символу добавляем по элементу списка
    (concat
      (append-letter (first l) alp)
      (make-list-of-strings alp (rest l))
      )))

(defn task-1-1
  ([alp l i]
   ; пока не достигли нужной длины сочетаний
   (if (> i 1)
     (make-list-of-strings alp (task-1-1 alp l (dec i)))
     l))
  ([alp i]
   (task-1-1 alp alp i))
  )

(println "\nRun task")
(def res (task-1-1 alphabet N))
(println res)