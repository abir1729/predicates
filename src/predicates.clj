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
;(def a-map {:a 1, :b 2})
;(defn test [{:keys [a b] :as a-map}]
 ; b)
;(test a-map)
;(keys a-map)

(defn whitespace? [character]
  (Character/isWhitespace character))

(defn blank? [string]
  (every? whitespace? string))

;(blank? " \t\n\t ") ;=> true
;(blank? "  \t a")   ;=> false
;(blank? "")         ;=> true

(def china {:name "China Miéville", :birth-year 1972})
(def octavia {:name "Octavia E. Butler"
              :birth-year 1947
              :death-year 2006})
(def kay {:name "Guy Gavriel Kay" :birth-year 1954})
(def dick {:name "Philip K. Dick", :birth-year 1928, :death-year 1982})
(def zelazny {:name "Roger Zelazny", :birth-year 1937, :death-year 1995})

(def authors #{china, octavia, kay, dick, zelazny})

(def cities {:title "The City and the City" :authors #{china}
             :awards #{:locus, :world-fantasy, :hugo}})
(def wild-seed {:title "Wild Seed", :authors #{octavia}})
(def lord-of-light {:title "Lord of Light", :authors #{zelazny}
                    :awards #{:hugo}})
(def deus-irae {:title "Deus Irae", :authors #{dick, zelazny}})
(def ysabel {:title "Ysabel", :authors #{kay}, :awards #{:world-fantasy}})
(def scanner-darkly {:title "A Scanner Darkly" :authors #{dick}})

(def books #{cities, wild-seed, lord-of-light,
             deus-irae, ysabel, scanner-darkly})

;((key->predicate :awards) ysabel)
;(:awards ysabel)
;(:awards wild-seed)
;(filter #(true) nil)

;(filter #{1 2 3} #{1 3 5})
;(count #{1 2})

(defn has-award? [book award]
  (let [awards (:awards book)]
    (not (empty?
          (filter #{award} awards)))))

;(has-award? ysabel :world-fantasy) ;=> true
;(has-award? scanner-darkly :hugo)  ;=> false

(defn HAS-ALL-THE-AWARDS? [book awards]
  (let [book-awards (:awards book)]
    (= awards
       (set (filter book-awards awards)))))

;(filter #{:locus :hugo} #{:locus})

;(HAS-ALL-THE-AWARDS? cities #{:locus})
;=> true
;(HAS-ALL-THE-AWARDS? cities #{:locus :world-fantasy :hugo})
;=> true
;(HAS-ALL-THE-AWARDS? cities #{:locus :world-fantasy :hugo :pulitzer})
;=> false
;(HAS-ALL-THE-AWARDS? lord-of-light #{:locus :world-fantasy :hugo})
;=> false
;(HAS-ALL-THE-AWARDS? lord-of-light #{:hugo})
;=> true
;(HAS-ALL-THE-AWARDS? scanner-darkly #{})
;=> true
;(some even? [1])

;(first (filter even? [1 3 4]))

(defn my-some [pred a-seq]
  (first (map pred (filter pred a-seq))))

;(filter even? [1 2 3 4])
;(some even? [1 3 5 7])       ;=> falsey
;(some even? [1 3 5 7 8])     ;=> true
;(some neg? [1 3 5 0 7 8])    ;=> falsey
;(some neg? [1 3 5 0 7 -1 8]) ;=> true
;(some neg? [])               ;=> falsey
;(some first [[false] [1]])   ;=> 1;
;(some first [[false] []])    ;=> falsey
;(some nil? [1 2])            ;=> falsey
;(some nil? [1 nil 2])        ;=> true

(defn my-every? [pred a-seq]
  :-)

(defn prime? [n]
  :-)
;^^
