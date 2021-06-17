(ns website.state
  (:require [reagent.core :as reagent]))

;; define your app data so that it doesn't get over-written on reload
(defonce state
  (reagent/atom {:hello "moto"}))
(defonce app-state (atom {:text "Hello world"}))