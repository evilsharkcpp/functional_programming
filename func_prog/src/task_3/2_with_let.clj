(ns task-3.2-with-let)
; Задача 3.2 использоватль последовательность вместо мемоизации

(defn trapezoid [f a b]
  ; h = b - a
  (* (* (+ (f a) (f b)) (- b a)) 0.5))

(defn integral [f a b h]
  (if (< a b)
    (+
      (trapezoid f a (+ a h))
      (integral f (+ a h) b h)
      )
    0
    ))

(defn get-antiderivative-value [func h]
  (reductions (fn [sum x] (+ sum (trapezoid func (* h x) (* h (inc x))))) (range))
  )

(defn get-antiderivative [func h]
  (let [t (get-antiderivative-value func h)]
    (fn [x] (nth t (int (/ x h))))
    )
  )

(defn sqr [x] (* x x))
(defn lazy-integral [func x h]
  ((get-antiderivative func h) x)
  )
(def sqr-antider-lazy (get-antiderivative sqr 0.05))
(defn -main [& args]
  (time (integral sqr 0 50 0.05))
  (time (lazy-integral sqr 50 0.05))
  (time (integral sqr 0 5 0.05))
  (time (lazy-integral sqr 5 0.05))
  (shutdown-agents)
  )