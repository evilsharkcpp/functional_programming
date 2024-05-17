; Задача 3.2 использоватль последовательность вместо мемоизации
(ns task-3.2)

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

(defn get-antiderivative-value [func h idx]
  (->>
    (reductions (fn [sum x] (trapezoid func (* h x) (* h (inc x)))) 0 (range 0 idx))
    (apply +)
    )
  )

(defn get-antiderivative [func h]
  (fn [x] (get-antiderivative-value func h (int (/ x h))))
  )

(defn sqr [x]  (* x x))
(defn lazy-integral [func x h]
  ((get-antiderivative func h) x)
  )

(defn -main [& args]
  (time (integral sqr 0 50 0.05))
  (time (lazy-integral sqr 50 0.05))
  (time (integral sqr 0 5 0.05))
  (time (lazy-integral sqr 5 0.05))
  (shutdown-agents)
  )