(ns flashcards.render
  (:require
    [flashcards.render.html :as html]
    [flashcards.storage :as storage]))

(defn id [x]
  (js/document.getElementById x))

(defn replace-with [k body]
  (.replaceWith (id k) body))

(defn div [x]
  (id (str "div" x)))

(defn pic [x]
  (id (str "pic" x)))

(defn show [x show?]
  (set! (.-style x) -display (if show? "" "none")))

(defn- show-div [i]
  (doseq [j [0 1]]
    (show (div j) (= i j))))

(defn start []
  (show-div 1))
(defn finish []
  (show-div 0))

(defn table []
  (replace-with "table" (html/table)))

(defn edit [v]
  (set! (id "textarea") -value v))

(defn value [i]
  (-> i id .-value))

(defn pics [k]
  (replace-with "div1" (html/pics k)))

(defn show-pic [i x]
  (show (pic i) x))

(defn transition [[i j]]
  (show-pic i false)
  (show-pic j true))
