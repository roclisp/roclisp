(ns roclisp.views.core
  (:require [roclisp.templates :as templates])
  (:use [noir.core :only [defpage]])
  (:use [metrics.timers :only [timer time!]])
  (:use [metrics.gauges :only [gauge]])
  (:use [metrics.counters :only [counter inc!]])
  (:use [metrics.histograms :only [histogram update!]])
  (:use [metrics.meters :only [meter mark!]]))


; (def next-meeting {:date "Thursday, February 23, 2012 at 6 PM"
;                    :topic "Writing The Roc Lisp Website in Noir"
;                    :location {:title "Coworking Rochester"
;                               :href "http://coworkingrochester.com/"}})
(def next-meeting nil)

(def hiccup-rendering (timer "hiccup-rendering"))
(def total-page-views (counter "total-page-views"))
(def page-views (meter "page-views" "views"))
(def page-size (histogram "page-size"))
(def meetings-planned (gauge "meetings-planned" (if next-meeting 1 0)))

(defpage "/" []
  (inc! total-page-views)
  (mark! page-views)
  (let [result (time! hiccup-rendering
                      (templates/home next-meeting))]
    (update! page-size (count result))
    result))

