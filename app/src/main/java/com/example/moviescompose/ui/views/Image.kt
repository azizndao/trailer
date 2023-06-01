package com.example.moviescompose.ui.views

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.moviescompose.R
import com.example.moviescompose.utils.ImageHelper


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NetworkImage(
  path: String?,
  modifier: Modifier = Modifier,
  contentDescription: String? = null,
  errorResId: Int = R.drawable.movie_placeholder
) {
  GlideImage(
    model = path?.let { ImageHelper.getImage(300, it) },
    contentDescription = contentDescription,
    contentScale = ContentScale.Crop,
    modifier = modifier.background(MaterialTheme.colorScheme.surfaceVariant),
  ) {
    it.error(errorResId)
  }
}