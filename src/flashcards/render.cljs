(ns flashcards.render
  (:require
    [flashcards.render.html :as html]
    [flashcards.storage :as storage]))

(defn id [x]
  (js/document.getElementById x))

(defn div [x]
  (id (str "div" x)))

(defn show-div [i]
  (doseq [j [0 1]]
    (set! (-> j div .-style) -display (if (= i j) "" "none"))))

(defn table []
  (.replaceWith (id "table") (html/table)))

(defn edit [v]
  (set! (id "textarea") -value v))

(defn value [i]
  (-> i id .-value))
