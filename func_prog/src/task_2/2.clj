(ns task-2.2)

;Задача 2.2.
; Реализуйте бесконечную последовательность простых чисел

; Фильтрация кратных чисел
(defn filter-multiples [prime nums]
  (remove (fn [x] (zero? (mod x prime))) nums))

; Генерация последовательности простых чисел
(defn sieve [s]
  (let [prime (first s)]
    (cons prime (lazy-seq
                  (sieve (filter-multiples prime (rest s)))))))

; Генерация бесконечной последовательности
(def primes (sieve (iterate inc 2)))

(defn print-nth-prime [seq n]
  (println (nth seq n)))

(print-nth-prime primes 30)