(ns website.pages.index
  (:require [reagent.core :as reagent]
            [website.state :refer [state]]))

(defn index-page [route parameters]
  (let [name (reagent/atom "Help me!")]
    [:div
     [:h1 "Index page!"]
     [:h3 "Name: " @name]
     [:h3 "State: " @state]
     [:h3 "Route: " @route]
     [:h3 "Parameters: " (.toString parameters)]
     [:div [:a {:href "/posts"} "Posts"]]]))
