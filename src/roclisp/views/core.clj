(ns roclisp.views.core
  (:require [roclisp.templates :as templates])
  (:use [noir.core :only [defpage]]))


(defpage "/" []
  (templates/home))

