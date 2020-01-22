(ns flashcards.render.html
  (:require
    [flashcards.storage :as storage]
    [crate.core :as crate]))

(defn- a [onclick body]
  [:a {:href "javascript: void(0)" :onclick onclick} body])

(defn- td [onclick & body]
  [:td {:style "padding: 5px"} (a onclick body)])

(defn- row [[x srcs]]
  [:tr
   (td #(js/flashcards.event.start-play x) (str x " (" (count srcs) " pics)"))
   (td #(js/flashcards.event.edit x) "Edit")
   (td #(js/flashcards.event.delete x) "X")])

(defn table []
  (crate/html
    [:table#table
     [:tbody
      (map row @storage/storage)]]))

(defn pic [i src]
  [:img {:id (str "pic" i)
         :src src
         :style "display: none"
         :onclick js/flashcards.event.play.play}])

(defn pics [k]
  (crate/html
    [:div#div1
     (map-indexed pic (@storage/storage k))]))
