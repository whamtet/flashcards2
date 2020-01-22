(ns flashcards.event
  (:require
    [flashcards.storage :as storage]
    [flashcards.render :as render]
    [clojure.string :as string]))

(defn ^:export upload [x]
  (storage/upload x)
  (render/table))

(defn ^:export edit [k]
  (render/edit
    (string/join "\n"
                 (concat [k] (@storage/storage k)))))

(defn ^:export download []
  (-> @storage/storage pr-str js/download))

(defn ^:export save []
  (let [[k & srcs] (-> "textarea" render/value .trim (.split "\n"))]
    (when (not-empty srcs)
      (storage/assoc k srcs)
      (prn 'storage @storage/storage)
      (render/table))))
