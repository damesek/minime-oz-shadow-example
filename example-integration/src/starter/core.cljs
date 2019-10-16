(ns starter.core
    (:require [reagent.core :as r]
              [app.vega :as vega]))

(vega/log "hello")

(defn group-data [& names]
  (apply concat (for [n names]
                  (map-indexed (fn [i y] {:x i :y y :col n}) (take 20
                                                                   (repeatedly #(rand-int 100)))))))


(def line-plot
  {:$schema "https://vega.github.io/schema/vega-lite/v3.4.json"
   :description "A simple line chart with embedded data."
   :data {:values (group-data "off" "error" "update" "run")}
   :vconcat [{:encoding {:x {:field "x" :type "quantitative" :title ""
                             :axis {:gridOpacity 0}}
                         :y {:field "y" :type "quantitative" :title ""}
                         :opacity {:value 0.7}
                         :color {:field "col"
                                 :title ""
                                 :type "nominal"
                                 :legend {:orient "none"
                                          :legendX 50
                                          :legendY 285
                                          :direction "horizontal"
                                          :offset 5}}}

              :width 270
              :heigth 150
              :mark {:type "line"
                     ;:interpolate "natural"
                     :strokeWidth 3
                     :point {:filled true
                             :fill "white"}}}
             {:encoding {:x {:field "y" :type "quantitative" :aggregate "sum" :title ""}
                         :opacity {:value 1}
                         :color {:field "col"
                                 :title ""
                                 :type "nominal"}}
              :width 270
              :mark {:type "bar"}}]})

(def line-plotter (r/atom (clj->js line-plot)))


(defonce greeting "hello2")
(def who "world")

(defn app []
  [:<>
   [:div greeting " " who]
   [vega/vega-lite @line-plotter]])

(defn stop []
  (js/console.log "Stopping..."))

(defn start []
  (js/console.log "Starting...")
  (r/render [app]
            (.getElementById js/document "app")))

(defn ^:export init []
  (start))
