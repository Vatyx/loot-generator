(ns kaleidoscopeloot.core
    (:require [incanter.stats :as i]
              [clojure.java.io :as io]
              [clojure.edn :as edn]))

(defn load-edn-string [resource-name]
    (-> (io/resource resource-name)
        slurp))

(defn huh-reader [something]
    (print something)
    something)

(def readers {'huh huh-reader})

(defn edn-read [resource-name]
    (let [edn-string (load-edn-string resource-name)
          string-reader (java.io.PushbackReader.
                            (java.io.StringReader. edn-string))
          edn-reader #(edn/read {:eof     nil
                                 :readers readers
                                 :default (fn [_ a] a)}
                               string-reader)]
        (into [] (take-while some?) (repeatedly edn-reader))))

(edn-read "items.edn")

(defn foo
    "I don't do a whole lot."
    [x]
    (println x "Hello, World!"))

(defn normal-float [mean sd]
    (float (i/sample-normal 1 :mean mean :sd sd)))

(defn normal-int [mean sd]
    (int (normal-float mean sd)))

(defn uniform-float [min max]
    (-> (i/sample-uniform 1 :min min :max max)
        first
        float))

(defn uniform-int [min max]
    (-> (i/sample-uniform 1 :min min :max max :integers true)
        first))

(uniform-int 1 10)

(defn roll [item]
    item)

(def item {
           :name          nil
           :powerlevel    nil
           :rarity        nil
           :augmentations nil
           :aspects       nil
           })

(i/sample-normal 20 :mean 2 :sd 5)

(comment
    (roll item)
    )
