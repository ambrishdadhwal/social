package com.social.user.reactor;

import java.util.ArrayList;
import java.util.List;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class SpringReactor
{

	public static void main(String[] args)
	{
		List<Integer> elements = new ArrayList<>();
		Flux.just(1, 2, 3, 4)
			.log().subscribe(new Subscriber<Integer>()
			{

				@Override
				public void onSubscribe(Subscription s)
				{
					s.request(Long.MAX_VALUE);
				}

				@Override
				public void onNext(Integer integer)
				{
					elements.add(integer);
				}

				@Override
				public void onError(Throwable t)
				{
				}

				@Override
				public void onComplete()
				{
				}
			});

		Mono<Integer> mono = Mono.just(1);
	}

}
