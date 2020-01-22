(ns flashcards.event.play
  (:require
    [flashcards.render :as render]))

(def to-play (atom []))

(defn ^:export play []
  (if-let [transition (first @to-play)]
    (do
      (render/transition transition)
      (swap! to-play rest))
    (render/finish)))
