(ns flashcards.render)

(defn id [x]
  (js/document.getElementById x))

(defn div [x]
  (id (str "div" x)))

(defn show-div [i]
  (doseq [j [0 1]]
    (set! (-> j div .-style) -display (if (= i j) "" "none"))))

