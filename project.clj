(defproject ccw/lein-ccw-deps "0.1.0"
  :description "Plugin providing a ccw-deps task for installing project dependencies
                in a lib directory. What it does precisely is: generate the project's uberjar,
                then unzip the uberjar in ${project-root}/lib directory, and removes from 
                it all the files that belong to the project (keeping only dependencies files)"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in-leiningen true
  :dependencies [[fs "1.3.3"]])
