(ns flashcards.event.play
  (:require
    [flashcards.render :as render]))

(def to-play (atom []))
(def to-show (atom []))

(defn ^:export play []
  (if-let [transition (first @to-play)]
    (do
      (render/transition transition)
      (swap! to-show rest)
      (swap! to-play rest))
    (render/finish)))

(defn ^:export play-entry [i]
  (let [entry (-> (str "entry" i) js/document.getElementById .-value)
        expected (some-> @to-show first (.split ";") second .trim)]
    (when (= entry expected)
      (play))))
