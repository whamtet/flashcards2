(ns flashcards.storage
  (:refer-clojure :exclude [assoc dissoc])
  (:require [alandipert.storage-atom :refer [local-storage]]
            [cljs.reader :refer [read-string]]))

(def storage (local-storage (atom {}) :storage))

(defn assoc [k v]
  (swap! storage cljs.core/assoc k v))

(defn dissoc [k]
  (swap! storage cljs.core/dissoc k))

(defn upload [x]
  (swap! storage merge (read-string x)))

(prn 'storage @storage)
