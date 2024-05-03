(ns task-1.2
  (:use task-1.params))
; Задача 1.2.
; Перепишите программу 1.1. так, чтобы все рекурсивныевызовы были хвостовыми
(load-file "params.clj")

;; добавляет элементы списка l к концу строки s
(defn append-letter
  ([s l acc]
   ; если список не пуст
   (if (> (count l) 0)
     ; если последний символ строки совпал с элементом списка
     (if (= (str (last s)) (first l))
       ; пропускаем его
       (recur s (rest l) acc)
       ; добавляем элемент списка к строке и идём дальше
       (recur  s
               (rest l)
               (concat acc (list (str s (first l))))))
     acc))
  ([s, l]
   (append-letter s l '()))
  )

(defn make-list-of-strings
  ([alp l acc]
   ; пока список не пуст
   (if (> (count l) 0)
     ; к каждому символу добавляем по элементу списка
     (recur alp
            (rest l)
            (concat acc (append-letter (first l) alp)))
     acc))
  ([alp l]
   (make-list-of-strings alp l '()))
  )

(defn task-1-2
  ([alp i acc]
   ; пока не достигли нужной длины сочетаний
   (if (> i 1)
     (recur alp
            (dec i)
            (make-list-of-strings alp acc))
     acc))
  ([alp i]
   (task-1-2 alp i alp))
  )

(println "\nRun task")
(def res (task-1-2 alphabet N))
(println res)