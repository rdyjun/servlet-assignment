let index = 1;
const movies = document.querySelectorAll('.moviePosterContainer').forEach(el => el.classList.remove('focus'));
const f = document.getElementById("first");
const s = document.getElementById("second");
const t = document.getElementById("third");
let className = ['animate-first', 'animate-focus', 'animate-second'];

const title = document.getElementById("movieNameKor");
const genre = document.getElementById("genre");
const release = document.getElementById("release");
const overView = document.getElementById("overView");
const runtime = document.getElementById("runtime");
const trailer = document.getElementById("trailer");
const voteOuter = document.getElementById("voteOuter");
const bg = document.getElementById("bg");
const focus = document.getElementsByClassName("animate-focus");
bg.style.backgroundImage = "url(" + movieData[0].poster + ")";

function switchMovie() {
    f.classList.remove(className[index % 3]);
    s.classList.remove(className[(index + 2) % 3]);
    t.classList.remove(className[(index + 1) % 3]);
    f.classList.add(className[(index + 1) % 3]);
    s.classList.add(className[(index + 3) % 3]);
    t.classList.add(className[(index + 2) % 3]);
    index += 1;

    trailer.src = movieData[(index - 1) % 3].videosLink;
    title.textContent = movieData[(index - 1) % 3].title;
    genre.textContent = movieData[(index - 1) % 3].category;
    release.textContent = movieData[(index - 1) % 3].releaseDate;
    overView.textContent = movieData[(index - 1) % 3].overView;
    runtime.textContent = movieData[(index - 1) % 3].runtime + "분";
    voteOuter.textContent = movieData[(index - 1) % 3].voteAverage;
    voteOuter.textContent = voteOuter.textContent.substring(0, 3);
    bg.style.backgroundImage = "url(" + movieData[(index - 1) % 3].poster + ")";
}
setInterval(switchMovie, 5000);