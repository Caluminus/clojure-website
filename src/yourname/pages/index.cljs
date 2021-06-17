(ns yourname.pages.index
  (:require [reagent.core :as reagent]))

(defn index-page [route]
  (let [name (reagent/atom "Help me!")]
    [:div
     [:h1 "Index page!"]
     [:h3 (:text name)]]))
