(ns leiningen.ccw-deps
  (:require [clojure.pprint :as pprint])
  (:require [leiningen.uberjar :as uberjar])
  (:require [fs.compression :as compress]
            [fs.core :as fs]))

(defn- relative-to
  "return the relative part of file from file-root"
  [root path]
  (when (fs/child-of? root path)
    (.substring (str path) (inc (count (str root))))))

(defn- empty-dir? 
  "Does path denote an empty directory?"
  [path]
  (not (seq (fs/list-dir path))))

(defn- delete-empty-hierarchy 
  "Delete path if it denotes an empty directory, and does so recursively with
   ancestors of path"
  [path]
  (when (empty-dir? path)
    (fs/delete-dir path)
    (recur (fs/parent path))))

(defn delete-path-and-empty-ancestors [path]
  (when (fs/exists? (fs/file path))
        (fs/delete (fs/file path))
        (delete-empty-hierarchy (fs/parent (fs/file path)))))

(defn ccw-deps
  "Pretty-print a representation of the project map."
  [{{:keys [target-dir] :or {target-dir "lib"}} :ccw-deps :as project} & keys]
  (let [uberjar (binding [*out* (java.io.StringWriter.)] 
                  ;; We rebind *out* to prevent uberjar to display anything
                  (uberjar/uberjar project))
        lib-dir (fs/file (:root project) target-dir)]
    (when (not= (.getCanonicalPath lib-dir) (.getCanonicalPath (fs/file (:root project))))
      (println "lib-dir not root, deleting content")
      (fs/delete-dir lib-dir)
      (fs/mkdir lib-dir))
    
    (compress/unzip (fs/file uberjar) lib-dir)
    
    (doseq [root [:source-paths :test-paths :resource-paths]
            root-path (root project)
            :let [paths (->> root-path
                          fs/file
                          file-seq 
                          (filter fs/file?)
                          (map #(->> %
                                  (relative-to root-path)
                                  (fs/file lib-dir))))]
            path paths]
      (delete-path-and-empty-ancestors path))))