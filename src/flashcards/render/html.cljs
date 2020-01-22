(ns flashcards.render.html
  (:require
    [flashcards.storage :as storage]
    [crate.core :as crate]))

(defn- a [onclick body]
  [:a {:href "javascript: void(0)" :onclick onclick} body])

(defn- td [onclick & body]
  [:td (a onclick body)])

(defn- row [[x srcs]]
  [:tr
   (td js/console.log (str x " (" (count srcs) ")"))
   (td #(js/flashcards.event.edit x) "Edit")
   (td js/console.log "X")])

(defn table []
  (crate/html
    [:table#table
     [:tbody
      (map row @storage/storage)]]))

(defn pics [k]
  (crate/html
    [:div#div1
     (for [src (@storage/storage k)]
       [:img {:src src :style "display: none"}])]))
