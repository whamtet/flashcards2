(ns flashcards.core
  (:require [flashcards.render]
            [flashcards.storage]
            [flashcards.event]))

(enable-console-print!)

(println "Hello world!")

(flashcards.render/table)
