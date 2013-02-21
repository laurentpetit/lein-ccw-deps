# lein-ccw-deps

Plugin providing a ccw-deps task for installing project dependencies in a lib directory.

## Usage

Put `[ccw/lein-ccw-deps "0.1.0"]` into the `:plugins` vector of your project.clj.


    $ lein ccw-deps

## How does it work?

- generates the project's uberjar,
- then unzips the uberjar in ${project-root}/lib directory
- and removes from it all the files that belong to the project (keeping only dependencies files)

## Todo

- Stop deleting `lib/` folder, and instead provide a hook to the `clean` task?

## License

Copyright © 2013 Laurent Petit

Distributed under the Eclipse Public License, the same as Clojure.
