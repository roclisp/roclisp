(ns roclisp.server
  (:use [metrics.ring.expose :only (expose-metrics-as-json)])
  (:require [noir.server :as server]))


(server/load-views "src/roclisp/views/")

(server/add-middleware expose-metrics-as-json)

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (server/start port {:mode mode
                        :ns 'roclisp})))

