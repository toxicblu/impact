var gulp      = require('gulp'),
    gutil     = require('gulp-util'),
    sass      = require('gulp-sass'),
    bs        = require('browser-sync').create(),
    del       = require('del');

var baseUri = 'src/main/webapp/';
var paths = {
  base: baseUri,
  sass: [baseUri + 'sass/**/*.scss', baseUri + 'sass/**/*.sass']
};

gulp.task('ts', function () {
  var Builder = require('jspm').Builder;
  var builder = new Builder();

  builder.buildStatic('src/main', paths.base + 'dist/impact.js', { minify: true, sourceMaps: true })
    .then(function() {
      console.log('transpilation complete');
    })
    .catch(function(err) {
        console.log(err);
      }
    );
});

gulp.task('sass', function () {
  return gulp.src(paths.sass)
    .pipe(sass())
    .pipe(gulp.dest(paths.base + '/css'))
    .pipe(bs.stream());
});

gulp.task('clean', function () {
  del(paths.base + 'dist/**')
});

gulp.task('serve', ['sass'], function () {
  bs.init({
    proxy: 'localhost:8080'
  });

  gulp.watch(paths.sass, ['sass']);
});

gulp.task('build', ['clean', 'sass', 'ts']);

gulp.task('default', ['serve']);
