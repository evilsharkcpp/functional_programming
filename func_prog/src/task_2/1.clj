(ns task-2.1)

; Задача 2.1.
; Напишите функцию, которая ищет n-ое простое число с помощью решета Эратосфена

; Фильтрация кратных чисел
(defn filter-multiples [prime nums]
  ;Заставляем считать сразу
  (doall (remove (fn [x] (zero? (mod x prime))) nums)))

; Алгоритм решета Эратосфена
(defn sieve-of-eratosthenes [n]
  (let [numbers (vec (range 2 (inc n)))]
    (loop [nums numbers
           primes []]
      (if (empty? nums)
        primes
        (let [prime (first nums)
              filtered (filter-multiples prime (rest nums))]
          (recur filtered (conj primes prime)))))))


(def max-num 30000)
; Нахождение n-го простого числа
(defn nth-prime [n]
  (let [prime (nth (sieve-of-eratosthenes max-num) (dec n))]
    (println "N-е простое число:" prime)
    prime))

(nth-prime 2000)

; Печать списка простых чисел до заданного числа
(defn print-primes [n]
  (let [primes (sieve-of-eratosthenes n)]
    (println "Простые числа до" n ":" primes)
    primes))

(print-primes 30)
