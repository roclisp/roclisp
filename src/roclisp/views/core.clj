(ns roclisp.views.core
  (:require [roclisp.templates :as templates])
  (:use [noir.core :only [defpage]]))


(def next-meeting {:date "Thursday, February 23, 2012 at 6 PM"
                   :topic "Writing The Roc Lisp Website in Noir"
                   :location {:title "Coworking Rochester"
                              :href "http://coworkingrochester.com/"}})

(defpage "/" []
  (templates/home next-meeting))

