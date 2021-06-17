(ns ^:figwheel-hooks yourname.hello-world
  (:require
   [reagent.core :as reagent :refer [atom]]
   [reitit.core :as r]
   [yourname.pages.index :refer [index-page]]
   [reagent.dom :as dom]
   [reitit.frontend.easy :as rfe]))


;; define your app data so that it doesn't get over-written on reload
(defonce state
  (reagent/atom {}))
(defonce app-state (atom {:text "Hello world"}))

(def router
  (r/router
   [["/"
     {:name :homepage
      :view index-page}]
    ["/tags/{*path}"
     {:name :tags
      :view 'post
      :parameters {:path string?}}]
    ["/posts/{*path}"
     {:name :posts
      :view 'post
      :parameters {:path string?}}]]))

(defn reagent-render [hiccup]
  (dom/render hiccup (js/document.getElementById "app")))

(defn render-page []
  (let [current-page (reagent/cursor state [:current-route])]
    (fn []
      [:div ((-> @current-page :data :view) current-page)])))

(defn ^:after-load init []
  (add-tap prn)
  (rfe/start!
   router
   (fn [m]
     (swap! state assoc :current-route m))
   ;; set to false to enable HistoryAPI
   {:use-fragment true})
  (reagent-render [render-page]))

(defonce start (init))

(prn "App-state:" @app-state)
