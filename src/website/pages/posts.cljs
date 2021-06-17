(ns website.pages.posts
  (:require [reagent.core :as reagent]
            [website.state :refer [state]]))

(defn posts-page [route parameters]
  (let [name (reagent/atom "Help me!")]
    [:div
     [:h1 "Post page!"]
     [:h3 "Name: " @name]
     [:h3 "State: " @state]
     [:h3 "Route: " @route]
     [:h3 "Parameters: " (.toString parameters)]]))
