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
    (not (or
          ((less-than n) k)
          ((equal-to n) k)))))
;((greater-than 2) 3)

;(filter (less-than 3) [1 2 3 4 5])   ;=> (1 2)
;(filter (less-than 4) [-2 12 3 4 0]) ;=> (-2 3 0)
;(filter (equal-to 2) [2 1 3 2.0])    ;=> (2 2.0)
;(filter (equal-to 2) [3 4 5 6])    ;=> ()

(def graphic-novels [{:name "Yotsuba 1" :series "Yotsuba"}
                     {:name "Yotsuba 5" :series "Yotsuba"}
                     {:name "Persepolis"}
                     {:name "That Yellow Bastard" :series "Sin City"}
                     {:name "The Hard Goodbye" :series "Sin City"}
                     {:name "Maus"}
                     {:name "Solanin"}
                     {:name "Monster 3" :series "Monster"}])
;(filter #(= "Yotsuba" (:series %1) graphic-novels))

(defn key->predicate [a-key]
  (fn [a-map]
    (contains? a-map a-key)))

;(filter (key->predicate "Name") [{"Name" "Joe"} {"Blargh" 3} {"Name" false}])
;({:1 2, 3 4} :1)

(defn set->predicate [a-set]
  (fn [a-val]
    (contains? a-set a-val)))

;(filter (set->predicate #{1 2 3})     [0 2 4 6])       ;=> (2)
;(filter (set->predicate #{1 2 3 nil}) [2 nil 4 nil 6]) ;=> (2 nil nil)

(defn pred-and [pred1 pred2]
  (fn [k]
    (and (pred1 k)
         (pred2 k))))

(defn pred-or [pred1 pred2]
  (fn [k]
    (or (pred1 k)
        (pred2 k))))

;((less-than 2) 3)
;((greater-than 2) 1)
;(filter #(and ((less-than 6) %) ((greater-than 2) %)) [1 4 5 7])
;(filter (pred-and (less-than 6) (greater-than 2)) [1 4 5 7])

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
