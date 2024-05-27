(ns task-3.2-solution)

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

(defn get-antiderivative-value [func h]
  (reductions (fn [sum x] (+ sum (trapezoid func (* h x) (* h (inc x))))) (range))
  )

(defn get-antiderivative [func h]
  (let [lazy-antider-value (get-antiderivative-value func h)]
    (fn [x] (nth lazy-antider-value (int (/ x h)))))
  )

(defn sqr [x] (* x x))

(def sqr-antider-lazy (get-antiderivative sqr 0.05))

(defn -main [& args]
  (time (integral sqr 0 10 0.05))
  (time (sqr-antider-lazy 10))
  (time (integral sqr 0 5 0.05))
  (time (sqr-antider-lazy 5))
  (shutdown-agents)
  )
