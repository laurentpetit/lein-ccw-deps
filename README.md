# lein-ccw-deps

Plugin providing a ccw-deps task for installing project dependencies in a lib directory.

What it does precisely is: generate the project's uberjar, then unzip the uberjar in ${project-root}/lib directory, and removes from it all the files that belong to the project (keeping only dependencies files)"

## Usage

Put `[ccw/lein-ccw-deps "0.1.0"]` into the `:plugins` vector of your project.clj.


    $ lein ccw-deps

## License

Copyright © 2013 Laurent Petit

Distributed under the Eclipse Public License, the same as Clojure.
