(ns clojure-person-thing-wooo.core
  (:require [clojure.walk]
            [clojure.pprint])
  (:gen-class))

(defn -main [& args]
  (let [purchase (slurp "purchases.csv")
        purchase (clojure.string/split-lines purchase)
        purchase (map (fn [line]
                      (clojure.string/split line #","))
                    purchase)
        header (first purchase)
        purchase (rest purchase)
        purchase (map (fn [line]
                        (interleave header line))
                      purchase)
        purchase (map (fn [line]
                        (apply hash-map line))
                      purchase)
        purchase (clojure.walk/keywordize-keys purchase)
        input (read-line)
        purchase (filter (fn [line]
                           (= input (:category line)))
                         purchase)]
    (spit "filtered-purchase.edn"
          (with-out-str (clojure.pprint/pprint purchase)))))
