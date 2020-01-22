(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'flashcards.core
   :output-to "out/flashcards.js"
   :output-dir "out"})
