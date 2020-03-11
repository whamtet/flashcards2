(ns flashcards.event
  (:require
    [flashcards.storage :as storage]
    [flashcards.render :as render]
    [flashcards.event.play :as play]
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
      (render/table)
      (render/edit ""))))

(defn ^:export start-play [k]
  (let [pics (vec (@storage/storage k))]
    (when (> (count pics) 1)
      (let [shuffled (shuffle (range (count pics)))]
        (reset! play/to-play (partition 2 1 shuffled))
        (reset! play/to-show (map pics shuffled))
        (render/start)
        (render/pics k)
        (render/show-pic (first shuffled) true)))))

(defn ^:export delete [k]
  (when (js/confirm (str "Delete " k "?"))
    (storage/dissoc k)
    (render/table)))

(defn ^:export clear []
  (render/edit ""))
