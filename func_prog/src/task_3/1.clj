(ns task-3.1)
; 3. Численное интегрирование.
; Общее условие:
; Реализовать функцию (оператор), принимающую аргументом функцию от одной переменной
; f и возвращающую функцию одной переменной, вычисляющую (численно) выражение:
; Можно использовать метод трапеций с постоянным шагом.
; При оптимизации исходить из того, что полученная первообразная будет использоваться
; для построения графика (т.е. вызываться многократно в разных точках)

; Задача 3.1.
; Оптимизируйте функцию с помощью мемоизации

(defn trapezoid [f a b]
  ; h = b - a
  (* (* (+ (f a) (f b)) (- b a)) 0.5) )

(defn integral [f a b h]
  (if (< a b)
    (+
      (trapezoid f a (+ a h))
      (integral f (+ a h) b h)
      )
    0
    ) )

(defn get-antiderivative-value [func h self-memoized idx]
  (if (= idx 0)
    0
    (+ (self-memoized func h self-memoized (dec idx))
       (trapezoid func (* h (dec idx)) (* h idx))
       )
    )
  )

(defn get-antiderivative [func h]
  (let [get-antiderivative-value-memoized (memoize get-antiderivative-value)]
    (fn [x] (get-antiderivative-value-memoized func h get-antiderivative-value-memoized (int (/ x h))))
    )
  )

(defn sqr [x] (Thread/sleep 1) (* x x))
(defn memoized-integral [func x h]
  ((get-antiderivative sqr h) x)
  )

(defn -main [& args]
  (time (integral sqr 0 1 0.05))
  (time (memoized-integral sqr 1 0.05))
  (time (integral sqr 0 0.5 0.05))
  (time (memoized-integral sqr 0.5 0.05))
  (shutdown-agents)
  )