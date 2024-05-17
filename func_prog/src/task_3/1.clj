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

(def trapezoid-mem (memoize trapezoid))

(defn integral-mem [f a b h]
  (if (< a b)
    (+
      (trapezoid-mem f a (+ a h))
      (integral f (+ a h) b h)
      )
    0
    )
  )

(defn f-linear [x] x)
(defn f-parabola [x] (* x x))
(defn f-polinom [x] (- (* x x) (* 3 x) 1))


(defn -main [& args]
  (time (integral f-polinom 0 50 0.05))
  (time (integral f-polinom 50 100 0.05))
  (time (integral-mem f-polinom -100 -50 0.05))
  (time (integral-mem f-polinom -50 0 0.05))
  )
