(ns ^:figwheel-hooks website.hello-world
  (:require
   [reagent.core :as reagent]
   [reitit.core :as r]
   [website.pages.index :refer [index-page]]
   [website.pages.posts :refer [posts-page]]
   [reagent.dom :as dom]
   [reitit.frontend.easy :as rfe]
   [goog.string :as gstring]
   [goog.string.format]
   [website.state :refer [state app-state]]))


(def router
  (r/router
   [["/"
     {:name :homepage
      :view index-page
      :parameters {}}]
    ;; ["/posts"
    ;;  {:name :tags
    ;;   :view posts-page
    ;;   :parameters {}}]
    ["/posts/:path"
     {:name :posts
      :view posts-page
      :parameters {:path string?}}]]))

(defn reagent-render [hiccup]
  (dom/render hiccup (js/document.getElementById "app")))

(defn render-page []
  (let [current-page (reagent/cursor state [:current-route])]
    (fn []
      [:div ((-> @current-page :data :view) current-page (-> @current-page :parameters))])))

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

;; (print (clojure.core/format "&.3f" 2))

(defn prn-test-path [path] (println (gstring/format "'%s' maps to '%s'" path (-> (r/match-by-path router path) :template))))

(prn "Start")
(prn-test-path "/")
(prn-test-path "/posts")
(prn-test-path "/posts/hello")
(prn "End")
