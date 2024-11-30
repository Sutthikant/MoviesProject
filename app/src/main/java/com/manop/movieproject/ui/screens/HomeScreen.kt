package com.manop.movieproject.ui.screens

import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.manop.movieproject.R
import com.manop.movieproject.network.MoviePhoto
import com.manop.movieproject.ui.theme.MovieProjectTheme

@Composable
fun HomeScreen(
    moviesUiState: MoviesUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(dimensionResource(R.dimen.padding_zero)),
) {
    when (moviesUiState) {
        is MoviesUiState.Loading -> LoadingScreen(
            modifier = modifier.fillMaxSize()
        )

        is MoviesUiState.Success -> PhotosGridScreen(
            photos = moviesUiState.photos,
            modifier = modifier.fillMaxWidth()
        )

        is MoviesUiState.Error -> ErrorScreen(
            retryAction = retryAction,
            modifier = modifier.fillMaxSize()
        )

    }
}

/**
 * ResultScreen displaying number of photos retrieved.
 */
@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = photos)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(dimensionResource(R.dimen.picture_size)),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_connection_error),
            contentDescription = ""
        )
        Text(
            text = stringResource(R.string.loading_failed),
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        )
        Button (onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun MoviesPhotoCard(
    photo: MoviePhoto,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Image(
            painter = rememberImagePainter(data = photo.imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
fun PhotosGridScreen(
    photos: List<MoviePhoto>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(dimensionResource(R.dimen.padding_zero))
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 120.dp), // Adaptive grid to fit posters dynamically
        modifier = modifier.padding(horizontal = 8.dp), // Adjust padding for better spacing
        contentPadding = contentPadding,
    ) {
        items(
            items = photos,
        ) { photo ->
            MoviesPhotoCard(
                photo,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ResultScreenPreview() {
    MovieProjectTheme {
        val mockData = List(10) { MoviePhoto("$it") }
        PhotosGridScreen(mockData)
    }
}
