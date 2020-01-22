(require '[cljs.build.api :as b])

(defn format-f [a b]
  (spit "index.html" (.replace (slurp "index.html") a b)))

(format-f "out/flashcards.js" "release/flashcards.js")

(println "Building ...")

(let [start (System/nanoTime)]
  (b/build "src"
    {:output-to "release/flashcards.js"
     :output-dir "release"
     :optimizations :advanced
     :verbose true})
  (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds"))
