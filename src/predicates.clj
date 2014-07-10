(ns predicates)

(defn sum-f [f g x]
  (+ (f x) (g x)))

(defn compare-func-maker [pred n]
  (fn [k] (pred k n)))

(defn less-than [n]
  (compare-func-maker < n))

(defn equal-to [n]
  (compare-func-maker == n))

(defn greater-than [n]
  (fn [k]
    (not (and
          (less-than n k)
          (equal-to n k)))))

;(filter (less-than 3) [1 2 3 4 5])   ;=> (1 2)
;(filter (less-than 4) [-2 12 3 4 0]) ;=> (-2 3 0)
;(filter (equal-to 2) [2 1 3 2.0])    ;=> (2 2.0)
;(filter (equal-to 2) [3 4 5 6])      ;=> ()

(defn set->predicate [a-set]
  :-)

(defn pred-and [pred1 pred2]
  :-)

(defn pred-or [pred1 pred2]
  :-)

(defn whitespace? [character]
  (Character/isWhitespace character))

(defn blank? [string]
  :-)

(defn has-award? [book award]
  :-)

(defn HAS-ALL-THE-AWARDS? [book awards]
  :-)

(defn my-some [pred a-seq]
  :-)

(defn my-every? [pred a-seq]
  :-)

(defn prime? [n]
  :-)
;^^
