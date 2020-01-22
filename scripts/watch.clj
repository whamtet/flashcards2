(require '[cljs.build.api :as b])

(defn format-f [a b]
  (spit "index.html" (.replace (slurp "index.html") a b)))

(format-f "release/flashcards.js" "out/flashcards.js")

(b/watch "src"
  {:main 'flashcards.core
   :output-to "out/flashcards.js"
   :output-dir "out"})
